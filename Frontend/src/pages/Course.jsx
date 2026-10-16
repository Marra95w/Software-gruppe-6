import { useParams } from "react-router-dom";
import { hentKursById } from "../Api/kursApi";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { formatDato, formatTid } from "../components/Dato";
import "../components/Course.css"
import '../components/courses.css';

export default function Course() {

    const { id } = useParams();
    const [kurs, setKurs] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

    useEffect(() => {
        const fetch = async () => {

            try {
                const result = await hentKursById(id);
                if (!result) {
                    throw new Error("Kurset ble ikke funnet")
                }
                setKurs(result);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetch();
    }, [id]);

    if (loading) return <p>Henting kurset...</p>
    if (error) return <p>Kunne ikke hente kurset...</p>

    return (


        <main className="course-details">
            <section className="choose-course">
                <img
                    className="course-strikk"
                    src="../src/assets/bilde/strikk.png"
                    alt="Strikke"
                />
                <div className="course-info">
                    <h1>{kurs.tittel}</h1>
                    <p>{kurs.beskrivelse}</p>
                    <p>Dato: {formatDato(kurs.startDatoTid)} til {formatDato(kurs.sluttDatoTid)} fra: {formatTid(kurs.startDatoTid)} til {formatTid(kurs.sluttDatoTid)}</p>
                </div>
            </section>

            <fieldset className="medlem-ikke-medlem">
                <img src="../src/assets/bilde/price-tag.svg" alt="prislapp" />
                <legend>Velg pris</legend>
                <label>
                    <input type="radio" name="pris" value="medlem" />
                    Pris for medlemmer: {kurs.prisMedlem} kr
                </label>
                <label>
                    <input type="radio" name="pris" value="ikke-medlem" />
                    Vanlig pris: {kurs.prisIkkeMedlem} kr
                </label>
            </fieldset>

            <section className="info-person">
                <form>
                    <fieldset>
                        <legend>Dine opplysninger</legend>

                        <img src="../src/assets/bilde/person.svg" alt="Person" />
                        <label htmlFor="fname">Navn: </label>
                        <input
                            type="text"
                            id="fname"
                            name="fname"
                            placeholder="Rigmor Nordmann"
                            required
                        />

                        <img src="../src/assets/bilde/e-post.svg" alt="E-post" />
                        <label htmlFor="email">E-post: </label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            placeholder="Rigmor@epost.no"
                            required
                        />
                        <img src="../src/assets/bilde/phone.svg" alt="Telefon" />
                        <label htmlFor="tel">Telefon: </label>
                        <input
                            type="tel"
                            id="tel"
                            name="tel"
                            placeholder="12345678"
                            required
                        />

                    </fieldset>
                </form>
            </section>
            <Link className="back-link" to="/Courses">Tilbake til kursoversikt</Link>
        </main>
    )

}

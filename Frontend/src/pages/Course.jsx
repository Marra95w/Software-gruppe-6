import { useParams } from "react-router-dom";
import { hentKursById } from "../Api/kursApi";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
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
                <p>Starter: {kurs.startDatoTid}</p>
                <p>Avslutter: {kurs.sluttDatoTid}</p>
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
                <img src="../src/assets/bilde/person.svg" alt="Person" />
                <h3>Dine opplysninger</h3>
                <form>
                    <img src="../src/assets/bilde/person.svg" alt="Person" />
                    <label htmlFor="fname">Fornavn: </label>
                    <input type="text" 
                        placeholder="Rigmor Nordmann"
                    />
                </form>
                <form>
                    <img src="../src/assets/bilde/e-post.svg" alt="mailboks" />
                    <label htmlFor="email"> E-post: </label>
                    <input type="email" 
                    placeholder="Rigmor@epost.no"
                    />
                </form>
                 <form>
                    <img src="../src/assets/bilde/phone.svg" alt="Telefon" />
                    <label htmlFor="tel"> Telefon: </label>
                    <input type="tel" 
                    placeholder="123456789"
                    />
                </form>

            <Link className="back-link" to="/Courses">Tilbake til kursoversikt</Link>


            </section>
        </main>
    )

}

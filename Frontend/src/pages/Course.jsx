import Registration from "../components/Registration";
import { useParams } from "react-router-dom";
import { hentKursById } from "../Api/kursApi";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { formatDato, formatTid } from "../components/Dato";
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
                    <p>{kurs.kursavholder}</p>
                    <p>{kurs.beskrivelse}</p>
                    <p>Dato {formatDato(kurs.startDatoTid)} til {formatDato(kurs.sluttDatoTid)}</p>
                    <p>Fra {formatTid(kurs.startDatoTid)} til {formatTid(kurs.sluttDatoTid)}</p>
                    <p>Tilgjengelig? {kurs.tilgjengelig}</p>
                    <p>Plasser: {kurs.maksDeltakere}</p>
                </div>
            </section>
            <section>
                {<Registration kurs={kurs} />}
            </section>
        </main>
    )

}

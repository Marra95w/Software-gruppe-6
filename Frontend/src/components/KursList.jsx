import { useEffect, useState } from "react"
import { hentAlleKurs } from "../Api/kursApi";
import { Link } from "react-router-dom";
import "./courses.css"
export default function KursList() {

    const [kursList, setKursList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);



    useEffect(() => {

        const fetchData = async () => {

            try {
                const data = await hentAlleKurs();

                setKursList(data);
            } catch (err) {

                setError(err.message);

            } finally {

                setLoading(false);
            }
        };
        fetchData();
    }, []);

    if (loading) return <p> Vi henter kursene...</p>;
    if (error) return <p> Error:{error} </p>;


   return (
    <section>
        {kursList.map((kurs) => (
            <Link className="course-link" to={`/courses/${kurs.id}`} key={kurs.id}>
                <article className="choose-course">
                    <img className="course-strikk" src="../src/assets/bilde/strikk.png" alt="Strikke" />
                        <div className="course-info">
                            <h3>{kurs.tittel}</h3>
                            <p>Fra {kurs.startDatoTid} til {kurs.sluttDatoTid}</p>
                            <p>Hvor: {kurs.kursavholder}</p>
                            <p>Pris: {kurs.prisMedlem} kr</p>
                            <p>Pris hvis du ikke er medlem: {kurs.prisIkkeMedlem} kr</p>
                            <p>Beskrivelse: {kurs.beskrivelse}</p>
                            <p>Tilgjengelig? {kurs.tilgjengelig}</p>
                            <p>MaksDeltakere: {kurs.maksDeltakere}</p>
                        </div>
                        <img
                            className="course-arrow"
                            src="../src/assets/bilde/arrow-down.svg"
                            alt="Pil"
                        />
                </article>
            </Link>
        ))}
    </section>
)};
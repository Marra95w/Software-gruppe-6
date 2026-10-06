import { useEffect, useState } from "react"
import { hentAlleKurs } from "../Api/kursApi";

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
            {kursList.map((kurs) =>

                <article key={kurs.tittel}> <h3>{kurs.tittel}</h3>
                    <p>Fra {kurs.startDatoTid} til {kurs.sluttDatoTid}</p>
                    <p>Hvor:{kurs.kursavholder} </p>
                    <p>Pris:{kurs.prisMedlem}</p>
                    <p>Pris hvis du ikke er medlem:{kurs.prisIkkeMedlem}</p>
                    <p>beskrivelse:{kurs.beskrivelse}</p>
                    <p>tilgjengelig? {kurs.tilgjengelig}</p>
                    <p>maksDeltakere: {kurs.maksDeltakere}</p>
                </article>
            )}

        </section>

    )
}
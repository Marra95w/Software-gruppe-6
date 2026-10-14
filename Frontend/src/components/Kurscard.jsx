import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";



export default function KursCard({ kurs }) {
    return (

        <Link to={"´/kurs/&{kurs.id}"}>
            <h1>{kurs.tittel}</h1>
            <p>{kurs.beskrivelse}</p>
        </Link>



    )


}
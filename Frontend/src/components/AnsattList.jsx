import { hentAnsatte } from "../Api/kursApi";
import { useEffect, useState } from "react";
import AnsattKort from "./AnsattKort";

export default function AnsattList() {
    const [ansatte, setAnsatte] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Henter alle ansatte fra API
        const fetchAnsatte = async () => {
            try {
                const data = await hentAnsatte();
                setAnsatte(data);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };

        fetchAnsatte();
    }, []);

    if (loading) {
        return <p>Henter ansatte...</p>;
    }

    if (error) {
        return <p>Error: {error.message}</p>;
    }

    return (
        //Få ut ansatte på nettsiden
        <section>
            {ansatte.map((ansatt) => (
                <AnsattKort key={ansatt.id} ansatt={ansatt} />
            ))}
        </section>
    );
}
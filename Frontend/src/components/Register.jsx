import { useState } from "react";
import { registerBruker } from "../Api/kursApi";
import "../css/Course.css";

export default function Register() {
    const [bruker, setBruker] = useState({
        fname: "",
        lname: "",
        email: "",
        password: "",
        tel: "",
    });
    const [error, setError] = useState("");
    const [message, setMessage] = useState("");


    const handleChange = (e) => {
        const { name, value } = e.target;
        setBruker((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        //Hvis fornavn er tomt
        if (!bruker.fname.trim()) {
            setError("Fornavn er påkrevd");
            return;
        }
        //Hvis etternavn er tomt
        if (!bruker.lname.trim()) {
            setError("Etternavn er påkrevd");
            return;
        }
        //Hvis e-post er tom
        if (!bruker.email.trim()) {
            setError("E-post er påkrevd");
            return;
        }
        //hvis passord er mindre enn 6 tegn
        if (bruker.password.length < 6) {
            setError("Passord må være minst 6 tegn");
            return;
        }
        //Hvis telefonnummer ikke er 8 siffer
        if (!/^\d{8}$/.test(bruker.tel)) {
            setError("Telefonnummer må være 8 siffer");
            return;
        }

        try {
            //Venter på registrering av bruker
            await registerBruker({
                navn: bruker.fname.trim(),
                etternavn: bruker.lname.trim(),
                epost: bruker.email.trim(),
                telefonnummer: Number(bruker.tel),
                passord: bruker.password,
                medlem: 0,
            });
            //Lagrer den nye brukeren i state
            setBruker({ fname: "", lname: "", email: "", password: "", tel: "" });
            setError("");
            setMessage("Registrering vellykket!");
            //Hvis det oppstår en feil
        } catch (err) {
            setMessage("");
            setError(err.message);
        }
    };

    return (
        <article className="course-container">
            <section className="info-person">
                <form onSubmit={handleSubmit} noValidate>
                    <fieldset>
                        <legend>Dine opplysninger</legend>

                        <span className="input-group">
                            <img src="../src/assets/bilde/person.svg" alt="Person" />
                            <label htmlFor="fname">Fornavn: </label>
                            <input type="text" id="fname" name="fname" placeholder="Rigmor"
                                value={bruker.fname} onChange={handleChange} required />
                        </span>

                        <span className="input-group">
                            <img src="../src/assets/bilde/person.svg" alt="Person" />
                            <label htmlFor="lname">Etternavn: </label>
                            <input type="text" id="lname" name="lname" placeholder="Nordmann"
                                value={bruker.lname} onChange={handleChange} required />
                        </span>

                        <span className="input-group">
                            <img src="../src/assets/bilde/e-post.svg" alt="E-post" />
                            <label htmlFor="email">E-post: </label>
                            <input type="email" id="email" name="email" placeholder="Rigmor@epost.no"
                                value={bruker.email} onChange={handleChange} required />
                        </span>

                        <span className="input-group">
                            <img src="../src/assets/bilde/password.svg" alt="Passord" />
                            <label htmlFor="password">Passord: </label>
                            <input type="password" id="password" name="password" placeholder="**********"
                                value={bruker.password} onChange={handleChange} required />
                        </span>

                        <span className="input-group">
                            <img src="../src/assets/bilde/phone.svg" alt="Telefon" />
                            <label htmlFor="tel">Telefon: </label>
                            <input type="tel" id="tel" name="tel" placeholder="12345678"
                                value={bruker.tel} onChange={handleChange} required />
                        </span>
                    </fieldset>

                    <button type="submit" className="back-link">Registrer</button>
                    {error && <p className="error">{error}</p>}
                    {message && <p className="success">{message}</p>}
                </form>
            </section>
        </article>
    );
}
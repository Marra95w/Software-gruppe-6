import { Link } from "react-router-dom";
import "../components/Course.css"

export default function Register(kurs) {
    return (
        <>
        <article className="course-container">

            <section className="info-person">
                <form>
                    <fieldset>
                        <legend>Dine opplysninger</legend>
                        <span className="input-group">
                            <img src="../src/assets/bilde/person.svg" alt="Person" />
                            <label htmlFor="fname">Fornavn: </label>
                            <input
                                type="text"
                                id="fname"
                                name="fname"
                                placeholder="Rigmor"
                                required
                            />
                        </span>

                        <span className="input-group">
                            <img src="../src/assets/bilde/person.svg" alt="Person" />
                            <label htmlFor="fname">Etternavn: </label>
                            <input
                                type="text"
                                id="fname"
                                name="fname"
                                placeholder="Nordmann"
                                required
                            />
                        </span>
                        
                        <span className="input-group">
                            <img src="../src/assets/bilde/e-post.svg" alt="E-post" />
                            <label htmlFor="email">E-post: </label>
                            <input
                                type="email"
                                id="email"
                                name="email"
                                placeholder="Rigmor@epost.no"
                                required
                            />
                        </span>

                        <span className="input-group">
                            <img src="../src/assets/bilde/phone.svg" alt="Telefon" />
                            <label htmlFor="tel">Telefon: </label>
                            <input
                                type="tel"
                                id="tel"
                                name="tel"
                                placeholder="12345678"
                                required
                            />
                        </span>
                        
                    </fieldset>
                </form>
            </section>
            <button className="back-link">Registrer</button>
        </article>
        </>
    )
}
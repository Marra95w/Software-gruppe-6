import { Link } from "react-router-dom";
import "../components/Course.css"
import { useState } from "react";

export default function Registration({ kurs }) {
    const [showLogin, setShowlogin] = useState(false)
    return (
        <article className="course-container">
            <fieldset className="medlem-ikke-medlem">
                <legend><img src="../src/assets/bilde/price-tag.svg" alt="prislapp" />Velg pris</legend>
                <label>
                    <input type="radio" name="pris" value="medlem" onChange={() => setShowlogin(true)}/>
                    Pris for medlemmer: {kurs.prisMedlem} kr
                </label>
                {showLogin && (
                    <Link className="login-link" to={"/Login"}>Logg inn som medlem</Link>
                )}
                <label>
                    <input type="radio" name="pris" value="ikke-medlem" onChange={() => setShowlogin(false)}/>
                    Vanlig pris: {kurs.prisIkkeMedlem} kr
                </label>
            </fieldset>

            <section className="info-person">
                <form>
                    <fieldset>
                        <legend>Dine opplysninger</legend>
                        <span className="input-group">
                            <img src="../src/assets/bilde/person.svg" alt="Person" />
                            <label htmlFor="fname">Navn: </label>
                            <input
                                type="text"
                                id="fname"
                                name="fname"
                                placeholder="Rigmor Nordmann"
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
            <button className="back-link">Gå til betaling</button>
            <Link className="back-link" to="/Courses">Tilbake til kursoversikt</Link>
        </article>
    )
}
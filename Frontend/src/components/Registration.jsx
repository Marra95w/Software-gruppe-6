import { Link } from "react-router-dom";
import "../components/Course.css"



export default function Registration({ kurs }) {
    return (
        <article className="course-container">
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
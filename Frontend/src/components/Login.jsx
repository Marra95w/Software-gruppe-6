import { Link } from "react-router-dom";
import "../components/Course.css"

export default function Login(kurs) {
    return (
        <>
        <article className="course-container">

            <section className="info-person">
                <form>
                    <fieldset>
                        <legend>Dine opplysninger</legend>

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
                        <img src="../src/assets/bilde/password.svg" alt="Passord" />
                            <label htmlFor="password">Passord: </label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                placeholder="********"
                                required
                            />
                        </span>


                        
                    </fieldset>
                </form>
            </section>
            <button className="back-link">Logg inn</button>
            <Link to="/Register"><button className="back-link">Registrer</button></Link>
        </article>
        </>
    )
}
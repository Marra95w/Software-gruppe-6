import Kurslist from '../components/KursList'
import { Link } from 'react-router-dom'
import '../components/ForsideHoved.css'
export default function ForsideHoved() {
    return (
        <>
        <main>
                <img className="strikkebilde" src="../src/assets/bilde/strikk.png" alt="Strikking" />
                <section className="intro">
                    <h2>Østfold Husflidslag</h2>
                    <p>Ivaretar og utvikler husflid og håndverk kulturelt, sosialt og økonomisk i Østfold</p>
                </section>
                <div className="image-design">
                    <img className="rose-design" src="../src/assets/bilde/rose.png" alt="rose" />
                </div>

            <section>
                <h2></h2>

                <article>
                    <h3>Kurs: </h3>
                  {<Kurslist />} 
                </article>
             </section>
             <section>
                <ul className="category-list">
                        <Link to="/courses">
                            <li className='course-card'>
                                    <img src="../src/assets/bilde/calendar.svg" alt="Calendar" />
                                    <h3>Kurs og aktiviteter</h3>
                                    <p>Se hva som skjer nær deg</p>
                                    <img src="../src/assets/bilde/arrow.svg" alt="Arrow" />
                            </li>
                        </Link>    

                        <Link to="./pages/Team">
                            <li className='local-card'>
                                    <img src="../src/assets/bilde/people.svg" alt="Human" />
                                    <h3>Lokallag</h3>
                                    <p>Finn ditt lokallag i Østfold</p>
                                    <img src="../src/assets/bilde/arrow.svg" alt="Arrow" />
                            </li>
                        </Link>
         
                        <Link to="./pages/Inspiration">
                            <li className='craft-card'>
                                    <img src="../src/assets/bilde/yarn.svg" alt="Yarn" />
                                    <h3>Håndverk og inspirasjon</h3>
                                    <p>Tips, ideer og artikler om håndverk</p>
                                    <img src="../src/assets/bilde/arrow.svg" alt="Arrow" />
                            </li>
                        </Link>
               
                        <Link to="./pages/OmOss">
                            <li className='about-card'>
                                    <img src="../src/assets/bilde/i.svg" alt="information" />
                                    <h3>Om oss</h3>
                                    <p>Hvem er vi og hva vi gjør</p>
                                    <img src="../src/assets/bilde/arrow.svg" alt="Arrow" />
                            </li>
                        </Link>
                   
                </ul>
             </section>

        </main>
        </>
    )
}
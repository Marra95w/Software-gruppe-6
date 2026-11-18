import { Link, Outlet } from "react-router-dom";
import { useEffect } from "react";
import Nav from './Nav'
const Layout = ({ }) => {

    return (
        <>
            <header>
                <Link to="/" className="home-link">
                    <img className="logo" src="../src/assets/bilde/rose.png" alt="rose" />
                    <h1>
                        Østfold Husflidslag
                    </h1>
                </Link>
                <nav>
                    <Link to="/Login" className='login-btn'>
                        Logg inn
                    </Link>
                    <p>Meny</p>
                    <Nav />
                </nav>
            </header>
            <main>

                {/* Outlet rendrer alle bane-elememnter (alt som skal være innenfor Route Layout i App.jsx) */}
                <Outlet />
            </main>

            <footer>
                <div className="image-design">
                    <img className="rose-design" src="../src/assets/bilde/rose.png" alt="rose" />
                </div>
                <section>
                    <h3>Kontakt oss</h3>
                    <p>Hjemmeside: fredrikstadhusflidslag.no</p>
                    <p>E-post: fredrikstad.husflidslag@gmail.com</p>
               </section>
               <section>
                    <h3>Besøk oss</h3>
                    <p>Emil Mørchs vei 25, 3. etg. (heis) på Sellebakk. Lett tilgjengelig med ferge over Glomma.</p>
               </section>
               <section>
                    <h3>Følg oss</h3>
                    <p>Facebook/Instagram: @fredrikstadhusflidslag</p>
               </section>
            </footer>

        </>
    )
}

export default Layout;
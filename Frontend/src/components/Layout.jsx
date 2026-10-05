import { Link, Outlet } from "react-router-dom";
import { useEffect } from "react";
import Nav from './Nav'
import "./Main.css"
const Layout = ({ }) => {

    return (
        <>
            <header>
                    <img class="logo" src="../src/assets/bilde/rose.png" alt="rose" /> 
                <h1>
                    Østfold Husflidslag
                </h1> 
                <nav>
                <p>Meny</p>
                   <Nav />
                </nav>
            </header>
            <main>
                    <img className="strikkebilde" src="../src/assets/bilde/strikk.png" alt="Strikking" />
                <section className="intro">
                    <h2>Østfold Husflidslag</h2>
                    <p>Ivaretar og utvikler husflid og håndverk kulturelt, sosialt og økonomisk i Østfold</p>
                </section>
                <div className="image-design">
                <img className="rose-design" src="../src/assets/bilde/rose.png" alt="rose" />
                </div>

                {/* Outlet rendrer alle bane-elememnter (alt som skal være innenfor Route Layout i App.jsx) */}
                <Outlet />
            </main>
            <footer> Kontakt oss</footer>

        </>
    )
}

export default Layout;
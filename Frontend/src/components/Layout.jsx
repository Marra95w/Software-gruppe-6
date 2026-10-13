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
                    <p>Meny</p>
                    <Nav />
                </nav>
            </header>
            <main>

                {/* Outlet rendrer alle bane-elememnter (alt som skal være innenfor Route Layout i App.jsx) */}
                <Outlet />
            </main>

            <footer>
                <h3>Kontakt oss</h3>
            </footer>

        </>
    )
}

export default Layout;
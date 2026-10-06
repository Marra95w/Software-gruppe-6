import { Link, Outlet } from "react-router-dom";
import { useEffect } from "react";
import Nav from './Nav'
import "./Main.css"
const Layout = ({ }) => {

    return (
        <>
            <header>
                <Link to="/" className="home-link">
                    <img class="logo" src="../src/assets/bilde/rose.png" alt="rose" /> 
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
               <p>Sentralbord:</p>
               <p>22 00 87 00</p>
               <p>Man-Tirs: 10:00-13:00</p>
               <p>Ons: Stengt</p>
               <p>To-Fre: 10:00-13:00</p>
               <p>Stengt: 11:30-12:00</p>
               <p>post@husflid.no</p>
               <h3>Besøk oss</h3>
               <p>Øvre slottsgate 2b,
                0157 Oslo</p>
                <h3>Snarveier</h3>
                <h3>Følg oss</h3>
                <p>Meld deg inn på nyhetsbrev</p>
                <p>Instagram</p>
                <p>Facebook</p>
                <p>Youtube</p>

            </footer>

        </>
    )
}

export default Layout;
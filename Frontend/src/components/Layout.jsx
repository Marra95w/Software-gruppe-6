import { Link, Outlet } from "react-router-dom";
import { useEffect } from "react";
import Nav from './Nav'
const Layout = ({ }) => {

    return (
        <>
            <header>
                <h1> Velkommen til Husflidslag Østfold </h1>
            </header>
            <nav>
                <Nav />
            </nav>
            <main>
                {/* Outlet rendrer alle bane-elememnter (alt som skal være innenfor Route Layout i App.jsx) */}
                <Outlet />
            </main>
            <footer> Kontakt oss også videre</footer>

        </>
    )
}

export default Layout;
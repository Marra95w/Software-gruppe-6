import { useState } from 'react'
import { Link } from 'react-router-dom'
import "./Nav.css"
import "./Main.css"
import "./Dropdown.css"
export default function Nav() {
    const [open, setOpen] = useState(false)
    return (

        <>
            <button onClick={() => setOpen(!open)} className='dropdown-trigger'>
                <img src="./public/menu.png" alt='Meny' className='menu-icon' />
            </button>
            {open && (
                <ul className="dropdown">
                    <li>
                        <img className='meny-rose' src="../src/assets/bilde/rose.png" alt="rose" />
                        <Link to="./pages/OmOss">Om Oss</Link>
                    </li>
                    <li>
                        <img className='meny-rose' src="../src/assets/bilde/rose.png" alt="rose" />
                        <Link to="./pages/Inspiration">Håndtverk og inspirasjon</Link>
                    </li>
                    <li>
                        <img className='meny-rose' src="../src/assets/bilde/rose.png" alt="rose" />
                        <Link to="./pages/Team">Lokallag</Link>
                    </li>
                    <li>
                        <img className='meny-rose' src="../src/assets/bilde/rose.png" alt="rose" />
                        <Link to="/courses">Kurs og aktiviteter</Link>
                    </li>
                     <li>
                        <img className='meny-rose' src="../src/assets/bilde/rose.png" alt="rose" />
                        <Link to="/">Hjem</Link>
                    </li>
                </ul>
            )}

        </>

        // <select className="dropdown">
        //     <option value="">Options</option>
        //     <option value="Option 2">Kurs og opplæring</option>
        //     <option value="Option 3">Håndtverk og aktiviteter</option>
        //     <option value="Option 4">Lokallag</option>
        //     <option value="Option 1">Kontakt Oss</option>
        // </select>

    );
}
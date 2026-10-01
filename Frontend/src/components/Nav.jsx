import { useState } from 'react'
import { Link } from 'react-router-dom'

export default function Nav() {
    const [open, setOpen] = useState(false)
    return (

        <>
            <button onClick={() => setOpen(!open)}>
                Dropdown
            </button>
            {open && (
                <ul className="dropdown">
                    <li>
                        <Link to="./pages/OmOss">Om Oss</Link>
                    </li>
                    <li>
                        <Link to="./pages/Inspiration">Håndtverk og inspirasjon</Link>
                    </li>
                    <li>
                        <Link to="./pages/Team">Lokallag</Link>
                    </li>
                    <li>
                        <Link to="./pages/Courses">Kurs og aktiviteter</Link>
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
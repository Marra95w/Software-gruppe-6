import { useState } from "react";
import "../components/courses.css";
import KursList from "../components/KursList";
export default function Course() {


    const [open, setOpen] = useState(false);
    return (
        <main>
            <h2>Kurs og aktiviteter</h2>
            <p>
                Her finner du kurs som hører til Østfold Husflidslag
                og andre lokallag
            </p>

            {/* søkefelt */}
            <form className="search">
                <img src="../src/assets/bilde/search.svg" alt="Search" />
                <input type="search"
                    placeholder="Søk etter kurs..."
                />
                <button type="submit">Søk</button>
            </form>

            {/* Datofiltrering */}
            <section className="filter">

                <div className="date-container">
                    <button
                        type="button"
                        className="date"
                        onClick={() => setOpen(!open)}
                    >
                        <img
                            src="../src/assets/bilde/calendar.svg"
                            alt="Kalender"
                        />
                        <p>Dato</p>
                        <img
                            className="arrow-down"
                            src="../src/assets/bilde/arrow-down.svg"
                            alt="pil ned"
                        />
                    </button>

                    {open && (
                        <div className="date-menu">
                            <label htmlFor="course-date">
                                Velg Dato
                            </label>
                            <input
                                type="date"
                                id="course-date"
                                name="coursedate"
                            />
                            <button>
                                Velg dato
                            </button>
                        </div>
                    )}
                </div>
                <button className="locator">
                    <img
                        src="../src/assets/bilde/locator.svg"
                        alt="Lokasjon"
                    />
                    <p>Sted</p>
                    <img
                        className="arrow-down"
                        src="../src/assets/bilde/arrow-down.svg"
                        alt="pil ned"
                    />
                </button>


                {/* kategori felt */}
                <button className="category">
                    <p>Kategori</p>
                    <img
                        className="arrow-down"
                        src="../src/assets/bilde/arrow-down.svg"
                        alt="pil ned"
                    />
                </button>
            </section>


            {/* Viser kurs */}
            <section>

                <KursList />
            </section>
        </main>
    );
}

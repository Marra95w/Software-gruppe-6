import KursList from "../components/KursList";
export default function Courses() {
    return (
        <main>
            <h2>Kurs og aktiviteter</h2>
            <p>her finner du kurs som hører til Østfold Husflidslag og andre lokallag</p>
            <form className="search">
                <img src="../src/assets/bilde/search.svg" alt="Search" />
                <input type="search"
                    placeholder="Søk etter kurs..."
                />
                <button type="submit">Søk</button>
            </form>
            <section>

                <KursList />
            </section>
        </main>
    )
}

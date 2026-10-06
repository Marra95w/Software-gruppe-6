import KursList from "../components/KursList";
export default function Course() {
    return (
        <main>
            <h2>Kurs og aktiviteter</h2>
            <p>her finner du kurs som hører til Østfold Husflidslag og andre lokallag</p>
            <form className="search">
                <input type="search" 
                img src="../src/assets/bilde/search.svg" alt="Search"
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

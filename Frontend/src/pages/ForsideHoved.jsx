import Kurslist from '../components/KursList'

export default function ForisdeHoved() {
    return (
        <>
            <h1> Velkommen til Forisden</h1>

            <section>
                <h2>Alle kursene vi tilbyr</h2>

                <article>
                    Kurs: {<Kurslist />}
                </article>

            </section>


        </>
    )
}
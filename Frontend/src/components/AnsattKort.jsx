export default function AnsattKort({ ansatt }) {
    return (
        //Et ansattkort
        <article >

            <img className="employee-image" src="../src/assets/bilde/person.svg" alt="Person" />

            <section className="employee-info">
                <h3>{ansatt.navn}</h3>
                <p>{ansatt.stilling}</p>
            </section>
            <address className="employee-contact">
                <a href={`tel:${ansatt.telefon}`}>
                    <img src="../src/assets/bilde/phone.svg" alt="Telefon" />
                    {ansatt.telefon}
                </a>

                <a href={`mailto:${ansatt.epost}`}>
                    <img src="../src/assets/bilde/e-post.svg" alt="E-post" />
                    {ansatt.epost}
                </a>
            </address>

        </article>
    );
}

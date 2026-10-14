

//Async function for å hente alle kurs i kurs_tabellen
export async function hentAlleKurs() {
    const response = await fetch("http://localhost:7001/kurs_tabell");
    if (!response.ok) {
        throw new Error("Kunne ikke hente kurs");
    }
    return response.json();
}



//Async function for å hent et spesific kurs ved bruk av params i urlen
export async function hentKursById(id) {

    const response = await fetch(`http://localhost:7001/kurs_tabell/${id}`);
    if (!response.ok) {
        throw new Error("Kurs ikke funnet");
    }
    return response.json();

}
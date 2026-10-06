export async function hentAlleKurs() {
    const response = await fetch("http://localhost:7001/kurs_tabell");
    if (!response.ok) {
        throw new Error("Kunne ikke hente kurs");
    }
    return response.json();
}
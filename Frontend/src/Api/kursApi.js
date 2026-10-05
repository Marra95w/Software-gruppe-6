export async function hentAlleKurs() {
    const response = await fetch("http://localhost:3000/kurs");
    if (!response.ok) {
        throw new Error("Kunne ikke hente kurs");
    }
    return response.json();
}
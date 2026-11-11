

//Async function for å hente alle kurs i kurs_tabellen
export async function hentAlleKurs() {
    const response = await fetch("http://localhost:8080/id");
    if (!response.ok) {
        throw new Error("Kunne ikke hente kurs");
    }
    return response.json();
}



//Async function for å hent et spesific kurs ved bruk av params i urlen
export async function hentKursById(id) {

    const response = await fetch(`http://localhost:8080/id/Henter_spesifikk_kurs_fra_databasen/${id}`);
    if (!response.ok) {
        throw new Error("Kurs ikke funnet");
    }
    return response.json();
}

// Async funtion for å lagre ny bruker i databasen
export async function registerBruker(bruker) {
    const response = await fetch("http://localhost:8080/Registrere_ny_bruker", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(bruker)
    });
    const tekst = await response.text();
    if (!response.ok) {
        throw new Error(tekst || "Kunne ikke registrere bruker");
    }
    return tekst ? JSON.parse(tekst) : null;
}
//Async function for å hente ansatte
export async function hentAnsatte() {
    const response = await fetch("http://localhost:8080/ansatt/hente_ansatte");
    if (!response.ok) {
        throw new Error("Kunne ikke hente ansatte");
    }
    return response.json();
}
export function formatDato(datoTidArray) {
    if (!Array.isArray(datoTidArray)) return "Ukjent dato";
    const [år, måned, dag] = datoTidArray;
    const dato = new Date(år, måned - 1, dag);
    return dato.toLocaleDateString("no-NO", {
        day: "numeric",
        month: "long",
        year: "numeric",
    });
}

export function formatTid(datoTidArray) {
    if (!Array.isArray(datoTidArray)) return "";
    const [år, måned, dag, time, minutt] = datoTidArray;
    const dato = new Date(år, måned - 1, dag, time, minutt);
    return dato.toLocaleTimeString("no-NO", {
        hour: "2-digit",
        minute: "2-digit",
    });
}
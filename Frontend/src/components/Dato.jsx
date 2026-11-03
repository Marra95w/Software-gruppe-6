export function formatDato(iso) {
    if (!iso) return "";
    return new Date(iso).toLocaleString("nb-NO", {
        day: "numeric",
        month: "numeric",
        year: "numeric",

    });
}
export function formatTid(iso) {
    if (!iso) return "";
    return new Date(iso).toLocaleTimeString("nb-NO", {
        hour: "2-digit",
        minute: "2-digit",
    });
}
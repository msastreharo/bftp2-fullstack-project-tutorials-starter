export default class BookApi {
    BOOK_API_URL = "/api/books";

    getBooks() {
        return fetch(this.BOOK_API_URL)
            .then(response => response.json())
    }
}
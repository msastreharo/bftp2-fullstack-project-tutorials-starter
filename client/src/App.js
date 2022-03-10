import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import BookApi from "./api/BookApi";

function App() {

    const bookApi = new BookApi()

    const [books, setBooks] = useState([])

    useEffect(() => {
        bookApi.getBooks().then(setBooks);
        console.log(books)
    }, [])

    return (
        <div className="App">
            <h1>Libritos</h1>
            <h2>Esta es nuestra biblioteca:</h2>
            <ul>

                {books.map(book => <li>{ `${book.title} (${book.author})`}</li>)}
            </ul>
        </div>
    );
}

export default App;

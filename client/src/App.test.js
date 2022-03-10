import {render, screen, waitFor} from '@testing-library/react';
import App from './App';
import BookApi from "./api/BookApi";

const fakeBooks = [
    {title: "Mujeres, Raza y Clase", author: "Angela Y. Davis"},
    {title: "El problema del trabajo", author: "Kathi Weeks"},
    {title: "Teoría de la Reproducción Social", author: "Tithi Battacharyya"}
];

jest.mock('./api/BookApi')

test('shows list of books', async () => {

    const bookApiMock = jest
        .spyOn(BookApi.prototype, 'getBooks')
        .mockImplementation(async () => {
            return Promise.resolve(fakeBooks);
        });

    render(<App/>);
    await waitFor(() => {
        expect(screen.getByText(/Mujeres, Raza y Clase/i)).toBeInTheDocument()
        expect(screen.getByText(/El problema del trabajo/i)).toBeInTheDocument()
        expect(screen.getByText(/Teoría de la Reproducción Social/i)).toBeInTheDocument()
        expect(bookApiMock).toHaveBeenCalledTimes(1)
    })
});

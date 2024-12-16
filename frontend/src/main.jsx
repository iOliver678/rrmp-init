import { StrictMode } from 'react'; // Enforces best practices and warnings in React.
import { createRoot } from 'react-dom/client'; // Used to render the application to the DOM.
import './index.css'; // Global styles for the app.
import App from './App.jsx'; // Main application component.
import { createBrowserRouter, RouterProvider } from "react-router-dom"; // React Router for handling navigation.
import SearchUI from './Pages/SearchUI.jsx'; // SearchUI page component.
import Layout from './components/Layout'; // Layout component to structure pages.
import About from './Pages/About'; // About page component.

/**
 * Defining the router configuration for the application.
 * 
 * The router uses a layout with nested routes:
 * - `/` renders the main `App` component.
 * - `/prof` renders the `SearchUI` component.
 * - `/about` renders the `About` component.
 */
const router = createBrowserRouter([
    {
        path: "/", // Root path
        element: <Layout />, // Layout wraps the child routes
        children: [
            {
                path: "/", // Main page
                element: <App />,
            },
            {
                path: "/prof", // SearchUI page
                element: <SearchUI />
            },
            {
                path: "/about", // About page
                element: <About />,
            }
        ]
    }
]);

// Rendering the app and wrapping it with StrictMode for better debugging and RouterProvider for routing.
createRoot(document.getElementById('root')).render(
  <StrictMode>
      <RouterProvider router={router} /> 
  </StrictMode>,
);

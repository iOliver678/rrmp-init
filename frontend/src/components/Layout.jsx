/**
 * Layout Component
 * Renders the Navbar and dynamically displays child components via React Router's Outlet.
 */

import { Outlet } from 'react-router-dom'; // For rendering nested routes
import Navbar from '../Navbar'; // Navbar component

function Layout() {
  return (
    <>
      <Navbar /> {/* Persistent Navbar */}
      <Outlet /> {/* Displays the child route component */}
    </>
  );
}

export default Layout; // Exports the Layout component

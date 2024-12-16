import { Link } from 'react-router-dom'; // Provides navigation between routes in a React SPA.
import './Navbar.css'; // CSS file for styling the Navbar component.
import logo from './assets/sjsurrmplogo.png'; // Logo image used in the navbar.

/**
 * Navbar Component
 * Renders a navigation bar with links to the Home and About pages.
 * Includes a logo in the center of the navigation bar.
 */
function Navbar() {
  return (
    <nav className="navbar"> {/* Main container for the navigation bar */}
      {/* Link to the Home page */}
      <Link to="/" className="nav-home">
        <button>Home</button>
      </Link>

      {/* Logo section */}
      <div className="nav-logo">
        <img src={logo} alt="SJSU RRMP Logo" /> {/* Displays the imported logo image */}
      </div>

      {/* Link to the About page */}
      <Link to="/about" className="nav-about">
        <button>About</button>
      </Link>
    </nav>
  );
}

export default Navbar; // Exports the Navbar component for use in other parts of the application.

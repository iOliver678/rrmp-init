// Importing necessary modules and assets
import React from 'react';
import './App.css'; // Styles specific to the App component
import { Link } from "react-router-dom"; // For navigation within the app
import logo from './assets/rrmp-logo.png'; // Logo for the app

/**
 * App Component
 * This is the main landing page of the application.
 * It displays a background logo, a header, and a button that navigates to the professor selection page.
 */
const App = () => {
    return (
        <div className="container"> {/* Main container for the page */}
            <div className="background-logo">
                <img src={logo} alt="SJSU Logo" /> {/* Displays the logo */}
            </div>
            <div className="header">
                <h1 className="title">Pick the Best Professor for Your Course</h1> {/* App title */}
            </div>
            {/* Button that links to the professor selection page */}
            <Link to="/prof" className="startButton">Start</Link>
        </div>
    );
};

export default App; // Exports the App component for use in the router

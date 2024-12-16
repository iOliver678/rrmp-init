/**
 * About Component
 * This component displays an "About" page with a video demo and a gallery of team members.
 * Each team member has an image, name, and role description displayed.
 */

import React from 'react';
import './About.css'; // Styles specific to the About page
import Oliver from '../assets/Oliver.jpg'; // Image for Oliver Majano
import Ryan from '../assets/ryan.png'; // Image for Ryan Wang
import Miko from '../assets/miko.png'; // Image for Miko Tonthat
import Victor from '../assets/Victor.png'; // Image for Victor Le
import demo from '../assets/rrmpdemo.mp4'; // Video demo file

// Team member details
const descriptions = [
  "Lead Developer", // Role description for Oliver
  "Developer", // Role description for Ryan
  "Developer", // Role description for Miko
  "Developer" // Role description for Victor
];
const names = [
  "Oliver Majano", // Name of the lead developer
  "Ryan Wang", // Name of a developer
  "Miko Tonthat", // Name of a developer
  "Victor Le" // Name of a developer
];
const images = [Oliver, Ryan, Miko, Victor]; // Array of team member images

function About() {
  return (
    <div className="about-page">
      {/* Video Demo */}
      <video controls>
        <source src={demo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>

      {/* Image gallery for team members */}
      <div className="image-gallery">
        {images.map((image, index) => (
          <div key={index} className="image-item">
            <img src={images[index]} alt={`Image ${index + 1}`} /> {/* Team member image */}
            <h1>{names[index]}</h1> {/* Team member name */}
            <p>{descriptions[index]}</p> {/* Team member role */}
          </div>
        ))}
      </div>
    </div>
  );
}

export default About; // Exports the About component for use in routing

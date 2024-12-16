/**
 * SearchUI Component
 * This component allows users to select a department and course to search for professors.
 * It integrates with a backend API to fetch and display professor details.
 */

import React, { useState } from 'react';
import './SearchUI.css';

const SearchUI = () => {
    // State hooks for dropdowns, selections, professors, and loading state
    const [deptDropdownOpen, setDeptDropdownOpen] = useState(false);
    const [courseDropdownOpen, setCourseDropdownOpen] = useState(false);
    const [selectedDept, setSelectedDept] = useState('');
    const [selectedCourse, setSelectedCourse] = useState('');
    const [professors, setProfessors] = useState([]);
    const [loading, setLoading] = useState(false);

    // Hardcoded list of departments and courses for the dropdowns
    const departments = {
        'Aerospace Engineering': ['AE162', 'AE165', 'AE167', 'AE168', 'AE169'],
        'African American Studies': ['AFAM102', 'AFAM134', 'AFAM151', 'AFAM159'],
        'American Studies': ['AMS1', 'AMS129', 'AMS169', 'AMS179'],
        'Anthropology': ['ANTH11', 'ANTH12', 'ANTH13', 'ANTH25'],
        'Art': ['ART1', 'ART2', 'ART3', 'ART12', 'ART13', 'ART14'],
        'Art History': ['ARTH70A', 'ARTH70B', 'ARTH72', 'ARTH110'],
        'Biology': ['BIOL10', 'BIOL20', 'BIOL21', 'BIOL30', 'BIOL31'],
        'Business': ['BUS1', 'BUS20', 'BUS021', 'BUS130', 'BUS140', 'BUS160'],
        'Chemical Engineering': ['CHE160', 'CHE161', 'CHE162', 'CHE163'],
        'Chemistry': ['CHEM1A', 'CHEM1B', 'CHEM30A', 'CHEM30B'],
        'Child Development': ['ChAD60', 'ChAD70', 'ChAD150', 'ChAD160'],
        'Chinese': ['CHIN1A', 'CHIN1B', 'CHIN25A', 'CHIN25B'],
        'Civil Engineering': ['CE95', 'CE112', 'CE120', 'CE121', 'CE130', 'CE131'],
        'Communication Studies': ['COMM20', 'COMM21', 'COMM41', 'COMM45'],
        'Computer Engineering': ['CMPE30', 'CMPE50', 'CMPE102', 'CMPE110', 'CMPE120', 'CMPE125', 'CMPE130', 'CMPE131', 'CMPE133', 'CMPE142', 'CMPE148'],
        'Computer Science': ['CS46A', 'CS46B', 'CS47', 'CS49J', 'CS146', 'CS147', 'CS149', 'CS151', 'CS152', 'CS154', 'CS156', 'CS157A', 'CS157B', 'CS157C', 'CS158A', 'CS158B', 'CS159', 'CS160', 'CS161', 'CS166', 'CS174', 'CS175'],
        'Design': ['DSIT10', 'DSIT103', 'DSIT104', 'DSIT105', 'DSIT107'],
        'Economics': ['ECON1A', 'ECON1B', 'ECON101', 'ECON102', 'ECON103A'],
        'Electrical Engineering': ['EE97', 'EE98', 'EE101', 'EE102', 'EE110', 'EE112', 'EE118', 'EE120', 'EE122', 'EE124', 'EE127', 'EE128', 'EE130', 'EE132'],
        'English': ['ENGL1A', 'ENGL1B', 'ENGL10', 'ENGL20', 'ENGL021', 'ENGL022', 'ENGL071', 'ENGL100A', 'ENGL100WB'],
        'Environmental Studies': ['ENVS1', 'ENVS10', 'ENVS100W', 'ENVS110', 'ENVS117', 'ENVS124'],
        'Finance': ['FIN120', 'FIN121', 'FIN122', 'FIN123', 'FIN128', 'FIN129'],
        'French': ['FREN1A', 'FREN1B', 'FREN25A', 'FREN25B', 'FREN101A', 'FREN101B'],
        'Geography': ['GEOG1', 'GEOG10', 'GEOG12', 'GEOG100', 'GEOG105', 'GEOG112'],
        'Geology': ['GEOL1', 'GEOL3', 'GEOL4', 'GEOL5', 'GEOL7', 'GEOL105'],
        'German': ['GERM1A', 'GERM1B', 'GERM25A', 'GERM25B'],
        'History': ['HIST10A', 'HIST10B', 'HIST15A', 'HIST15B', 'HIST20A', 'HIST20B'],
        'Industrial Design': ['DSID121', 'DSID122', 'DSID123', 'DSID124', 'DSID125', 'DSID126'],
        'Industrial Technology': ['TECH60', 'TECH62', 'TECH63', 'TECH65', 'TECH66', 'TECH67','TECH115', 'TECH136', 'TECH145'],
        'Japanese': ['JPN1A', 'JPN1B', 'JPN25A', 'JPN25B', 'JPN101A', 'JPN101B'],
        'Justice Studies': ['JS10', 'JS12', 'JS25', 'JS100W', 'JS101', 'JS102', 'JS103'],
        'Kinesiology': ['KIN1', 'KIN2', 'KIN14', 'KIN15A', 'KIN15B', 'KIN20'],
        'Linguistics': ['LING20', 'LING21', 'LING101', 'LING107', 'LING108', 'LING111'],
        'Mathematics': ['MATH19', 'MATH30', 'MATH30P', 'MATH31', 'MATH32', 'MATH42', 'MATH70', 'MATH71', 'MATH100W', 'MATH102', 'MATH112', 'MATH123', 'MATH133A', 'MATH161A'],
        'Mechanical Engineering': ['ME20', 'ME30', 'ME101', 'ME106', 'ME111', 'ME113', 'ME114', 'ME120'],
        'Meteorology': ['METR10', 'METR12', 'METR100W', 'METR121A', 'METR121B', 'METR123'],
        'Music': ['MUSC1A', 'MUSC1B', 'MUSC2A', 'MUSC2B', 'MUSC3A', 'MUSC3B'],
        'Nursing': ['NURS10', 'NURS12', 'NURS20', 'NURS22', 'NURS24', 'NURS33'],
        'Nutrition': ['NUFS9', 'NUFS16', 'NUFS20', 'NUFS106A', 'NUFS106B', 'NUFS108A'],
        'Philosophy': ['PHIL9', 'PHIL10', 'PHIL12', 'PHIL57', 'PHIL61', 'PHIL66'],
        'Physics': ['PHYS2A', 'PHYS2B', 'PHYS50', 'PHYS51', 'PHYS52', 'PHYS158'],
        'Political Science': ['POLS1', 'POLS2', 'POLS3', 'POLS4', 'POLS15', 'POLS20'],
        'Psychology': ['PSYC1', 'PSYC2', 'PSYC18', 'PSYC100W', 'PSYC102', 'PSYC110'],
        'Public Health': ['PH1', 'PH15', 'PH100W', 'PH104', 'PH135', 'PH140', 'PH158', 'PH161'],
        'Radio, TV and Film': ['RTVF10', 'RTVF20', 'RTVF21', 'RTVF30', 'RTVF31', 'RTVF110'],
        'Recreation': ['RECL10', 'RECL90', 'RECL100W', 'RECL110', 'RECL112', 'RECL132'],
        'Religious Studies': ['RELS10', 'RELS70', 'RELS90', 'RELS100W', 'RELS104', 'RELS114'],
        'Social Work': ['SCWK10', 'SCWK11', 'SCWK111', 'SCWK120', 'SCWK130', 'SCWK140'],
        'Sociology': ['SOCI1', 'SOCI2', 'SOCI3', 'SOCI100W', 'SOCI101', 'SOCI102'],
        'Software Engineering': ['SE46A', 'SE46B', 'SE131', 'SE133', 'SE134', 'SE137', 'SE157A', 'SE157B', 'SE165', 'SE172', 'SE174'],
        'Spanish': ['SPAN1A', 'SPAN1B', 'SPAN25A', 'SPAN25B', 'SPAN101A', 'SPAN101B'],
        'Statistics': ['STAT95', 'STAT115', 'STAT235', 'STAT245', 'STAT255', 'STAT260'],
        'Teacher Education': ['EDTE162', 'EDTE180', 'EDTE190', 'EDTE224', 'EDTE250', 'EDTE260'],
        'Theatre Arts': ['TA5', 'TA10', 'TA11', 'TA13', 'TA15', 'TA17'],
        'Urban Planning': ['URBP101', 'URBP120', 'URBP124', 'URBP133', 'URBP142', 'URBP148'],
        'Women\'s Studies': ['WOMS10', 'WOMS20', 'WOMS101', 'WOMS102', 'WOMS112', 'WOMS160']
    };

    // Filters courses based on the selected department
    const getFilteredCourses = () => {
        return selectedDept ? departments[selectedDept] || [] : [];
    };

    // Handles department selection
    const handleDeptSelect = (dept) => {
        setSelectedDept(dept);
        setSelectedCourse('');
        setDeptDropdownOpen(false);
        setProfessors([]);
    };

    // Handles course selection
    const handleCourseSelect = (course) => {
        setSelectedCourse(course);
        setCourseDropdownOpen(false);
        setProfessors([]);
    };

    // Fetches professors for the selected course from the backend API
    const handleSearch = async () => {
        if (!selectedCourse) return;

        setLoading(true);
        setProfessors([]);

        try {
            const response = await fetch(`http://localhost:8080/professors?course=${selectedCourse}`);
            if (!response.ok) {
                throw new Error('Failed to fetch professors');
            }
            const data = await response.json();
            setProfessors(data);
        } catch (error) {
            console.error('Search error:', error);
            setProfessors([]);
        } finally {
            setLoading(false);
        }
    };

    // Constructs a URL to view professor details on RateMyProfessors
    const getProfessorUrl = (tid) => {
        return `https://www.ratemyprofessors.com/professor/${tid}`;
    };

    return (
        <div className="centered-container">
            <div className="search-container">
                <h1>Find Professors</h1>

                {/* Department dropdown */}
                <div className="search-box">
                    <label>Department:</label>
                    <input
                        type="text"
                        placeholder="Select a department..."
                        value={selectedDept}
                        readOnly
                        onClick={() => setDeptDropdownOpen(!deptDropdownOpen)}
                        className="search-input"
                    />
                    {deptDropdownOpen && (
                        <div className="dropdown-menu">
                            {Object.keys(departments).map((dept) => (
                                <div
                                    key={dept}
                                    className="dropdown-item"
                                    onClick={() => handleDeptSelect(dept)}
                                >
                                    {dept}
                                </div>
                            ))}
                        </div>
                    )}
                </div>

                {/* Course dropdown */}
                <div className="search-box">
                    <label>Course:</label>
                    <input
                        type="text"
                        placeholder="Select a course..."
                        value={selectedCourse}
                        readOnly
                        onClick={() => selectedDept && setCourseDropdownOpen(!courseDropdownOpen)}
                        className={`search-input ${!selectedDept ? 'disabled' : ''}`}
                    />
                    {courseDropdownOpen && (
                        <div className="dropdown-menu">
                            {getFilteredCourses().map((course) => (
                                <div
                                    key={course}
                                    className="dropdown-item"
                                    onClick={() => handleCourseSelect(course)}
                                >
                                    {course}
                                </div>
                            ))}
                        </div>
                    )}
                </div>

                {/* Search button */}
                <button
                    className="search-button"
                    onClick={handleSearch}
                    disabled={!selectedCourse}
                >
                    Search
                </button>
            </div>

            {/* Loading indicator */}
            {loading && <p className="loading-text">Loading...</p>}

            {/* Results table */}
            {professors.length > 0 && !loading && (
                <div className="results-container">
                    <table className="results-table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Rating</th>
                                <th>Number of Ratings</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {professors.map((professor) => (
                                <tr key={professor.tid}>
                                    <td>
                                        <a
                                            href={getProfessorUrl(professor.tid)}
                                            target="_blank"
                                            rel="noopener noreferrer"
                                            className="professor-link"
                                        >
                                            {professor.tFname} {professor.tLname}
                                        </a>
                                    </td>
                                    <td className={`rating ${professor.rating_class}`}>
                                        {professor.overall_rating || 'N/A'}
                                    </td>
                                    <td>{professor.tNumRatings || 0}</td>
                                    <td className={`status ${professor.rating_class}`}>
                                        {professor.rating_class || 'N/A'}
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}

            {/* No results message */}
            {!loading && professors.length === 0 && selectedCourse && (
                <p className="no-results">No professors found for {selectedCourse}.</p>
            )}
        </div>
    );
};

export default SearchUI;

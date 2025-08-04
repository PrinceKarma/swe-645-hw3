import React, { useState, useEffect } from 'react';

const SurveyForm = ({ survey, onSave, onCancel }) => {
    // State to hold the form data
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        streetAddress: '',
        city: '',
        state: '',
        zip: '',
        telephoneNumber: '',
        email: '',
        dateOfSurvey: '',
        likedMost: '',
        interestSource: '',
        recommendationLikelihood: '',
    });

    // When the 'survey' prop changes, update the form data
    useEffect(() => {
        if (survey) {
            setFormData(survey);
        }
    }, [survey]);

    // Handle changes to form fields
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(formData);
    };

    return (
        <form onSubmit={handleSubmit} className="survey-form">
            <h2>{survey ? 'Edit Survey' : 'Add Survey'}</h2>
            <input name="firstName" placeholder="First Name" value={formData.firstName} onChange={handleChange} required />
            <input name="lastName" placeholder="Last Name" value={formData.lastName} onChange={handleChange} required />
            <input name="streetAddress" placeholder="Street Address" value={formData.streetAddress} onChange={handleChange} required />
            <input name="city" placeholder="City" value={formData.city} onChange={handleChange} required />
            <input name="state" placeholder="State" value={formData.state} onChange={handleChange} required />
            <input name="zip" placeholder="Zip" value={formData.zip} onChange={handleChange} required />
            <input name="telephoneNumber" placeholder="Telephone" value={formData.telephoneNumber} onChange={handleChange} required />
            <input name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
            <input name="dateOfSurvey" type="date" value={formData.dateOfSurvey} onChange={handleChange} required />
            
            <select name="likedMost" value={formData.likedMost || ''} onChange={handleChange}>
                <option value="">What did you like most about the campus?</option>
                <option value="Students">Students</option>
                <option value="Location">Location</option>
                <option value="Campus">Campus</option>
                <option value="Atmosphere">Atmosphere</option>
                <option value="Dorm Rooms">Dorm Rooms</option>
                <option value="Sports">Sports</option>
            </select>

            <select name="interestSource" value={formData.interestSource || ''} onChange={handleChange}>
                <option value="">How did you become interested in the university?</option>
                <option value="Friends">Friends</option>
                <option value="Television">Television</option>
                <option value="Internet">Internet</option>
                <option value="Other">Other</option>
            </select>

            <select name="recommendationLikelihood" value={formData.recommendationLikelihood || ''} onChange={handleChange}>
                <option value="">How likely are you to recommend the university?</option>
                <option value="Very Likely">Very Likely</option>
                <option value="Likely">Likely</option>
                <option value="Unlikely">Unlikely</option>
            </select>

            <div className="form-buttons">
                <button type="submit" className="save-button">Save</button>
                <button type="button" onClick={onCancel} className="cancel-button">Cancel</button>
            </div>
        </form>
    );
};

export default SurveyForm;

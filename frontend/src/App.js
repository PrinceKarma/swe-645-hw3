import React, { useState, useEffect } from 'react';
import SurveyList from './components/SurveyList';
import SurveyForm from './components/SurveyForm';
import SurveyDetail from './components/SurveyDetail';
import SurveyCount from './components/SurveyCount';
import api from './services/api';
import './App.css';

function App() {
    const [surveys, setSurveys] = useState([]);
    const [selectedSurvey, setSelectedSurvey] = useState(null);
    const [viewingSurvey, setViewingSurvey] = useState(null);
    const [isFormVisible, setIsFormVisible] = useState(false);
    const [surveyCount, setSurveyCount] = useState(0);

    useEffect(() => {
        fetchSurveys();
        fetchSurveyCount();
    }, []);

    const fetchSurveys = async () => {
        try {
            const response = await api.get('/api/surveys');
            setSurveys(response.data);
        } catch (error) {
            console.error('Error fetching surveys:', error);
        }
    };

    const fetchSurveyCount = async () => {
        try {
            const response = await api.get('/api/surveys/count');
            setSurveyCount(response.data);
        } catch (error) {
            console.error('Error fetching survey count:', error);
        }
    };

    const handleSave = async (survey) => {
        try {
            if (survey.id) {
                await api.put(`/api/surveys/${survey.id}`, survey);
            } else {
                await api.post('/api/surveys', survey);
            }
            fetchSurveys();
            fetchSurveyCount(); // Update count after saving
            setSelectedSurvey(null);
            setIsFormVisible(false);
        } catch (error) {
            console.error('Error saving survey:', error);
        }
    };

    const handleEdit = (survey) => {
        setSelectedSurvey(survey);
        setViewingSurvey(null);
        setIsFormVisible(true);
    };

    const handleView = (survey) => {
        setViewingSurvey(survey);
        setSelectedSurvey(null);
        setIsFormVisible(false);
    };

    const handleDelete = async (id) => {
        try {
            await api.delete(`/api/surveys/${id}`);
            fetchSurveys();
            fetchSurveyCount(); // Update count after deleting
        } catch (error) {
            console.error('Error deleting survey:', error);
        }
    };

    const handleAddNew = () => {
        setSelectedSurvey(null);
        setViewingSurvey(null);
        setIsFormVisible(true);
    };

    const handleCancel = () => {
        setSelectedSurvey(null);
        setIsFormVisible(false);
    };

    const handleBackToList = () => {
        setViewingSurvey(null);
    };

    // Determine what to render
    const renderContent = () => {
        if (isFormVisible) {
            return <SurveyForm survey={selectedSurvey} onSave={handleSave} onCancel={handleCancel} />;
        }
        if (viewingSurvey) {
            return <SurveyDetail survey={viewingSurvey} onBack={handleBackToList} />;
        }
        return (
            <>
                <button onClick={handleAddNew} className="add-new-button">Add New Survey</button>
                <SurveyCount count={surveyCount} />
                <SurveyList surveys={surveys} onEdit={handleEdit} onDelete={handleDelete} onView={handleView} />
            </>
        );
    };

    return (
        <div className="App">
            <header className="App-header">
                <h1>Student Survey</h1>
            </header>
            <main>
                {renderContent()}
            </main>
        </div>
    );
}

export default App;

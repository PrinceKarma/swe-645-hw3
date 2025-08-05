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

    const renderContent = () => {
        if (isFormVisible) {
            return <SurveyForm survey={selectedSurvey} onSave={handleSave} onCancel={handleCancel} />;
        }
        if (viewingSurvey) {
            return <SurveyDetail survey={viewingSurvey} onBack={handleBackToList} />;
        }
        return (
            <div className="home-container">
                <div className="home-header">
                    <SurveyCount count={surveyCount} />
                    <button onClick={handleAddNew} className="button-primary">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><line x1="12" y1="5" x2="12" y2="19"></line><line x1="5" y1="12" x2="19" y2="12"></line></svg>
                        <span>Add New Survey</span>
                    </button>
                </div>
                <SurveyList surveys={surveys} onEdit={handleEdit} onDelete={handleDelete} onView={handleView} />
            </div>
        );
    };

    return (
        <div className="App">
            <header className="App-header">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg>
                <h1>Student Survey</h1>
            </header>
            <main>
                {renderContent()}
            </main>
        </div>
    );
}

export default App;

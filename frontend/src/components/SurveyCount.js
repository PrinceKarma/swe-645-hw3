import React from 'react';

const SurveyCount = ({ count }) => {
    return (
        <div className="survey-count">
            <h3>Total Surveys: {count}</h3>
        </div>
    );
};

export default SurveyCount;

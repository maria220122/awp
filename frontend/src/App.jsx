import React from 'react';
import {
  BrowserRouter as Router,
  Route,
} from 'react-router-dom';
import SignUpForm from './pages/SignUpForm';
import SignInForm from './pages/SignInForm';
import Profile from './pages/Profile';

const App = () => {
  return (
    <Router basename="/">
      <>
        <Route exact path="/" component={SignUpForm}></Route>
        <Route path="/sign-in" component={SignInForm}></Route>
        <Route path="/profile" component={Profile}></Route>
      </>
    </Router>
  );
};

export default App;

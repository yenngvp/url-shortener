import {Fragment, useEffect, useState} from "react";
import './App.css';
import SideNav, {NavItem, NavIcon, NavText} from '@trendmicro/react-sidenav';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import {Dashboard, GenerateLink, Redirect, Setting} from './component';

function App() {
  const [menuExpanded, ] = useState(true);
  const [selectedPage, setSelectedPage] = useState('dashboard');

  useEffect(() => {
    const location = window.location;
    const paths = location.pathname.split('/');

    if (paths.length > 0) {
      setSelectedPage(paths[paths.length - 1]);
    } else {
      setSelectedPage('dashboard');
    }
  }, [])

  return (
    <Router>
      <Route render={({location, history}) => (
        <Fragment>
          <SideNav
            expanded={menuExpanded}
            onToggle={() => console.log('toggling menu...')}
            onSelect={(selected) => {
              setSelectedPage(selected);
              const to = selected === 'dashboard' ? '/' : '/' + selected;
              if (location.pathname !== to) {
                history.push(to);
              }
            }}
            className={"nav-background"}
          >
            <SideNav.Nav selected={selectedPage}>
              <NavItem eventKey="dashboard">
                <NavIcon>
                  <i className="fa fa-tachometer" aria-hidden="true" style={{fontSize: '1.75em'}}></i>
                </NavIcon>
                <NavText style={{fontSize: '1.25em'}}>
                  Dashboard
                </NavText>
              </NavItem>
              <NavItem eventKey="generate-link">
                <NavIcon>
                  <i className="fa fa-link" aria-hidden="true"
                     style={{fontSize: '1.75em', verticalAlign: 'middle'}}></i>
                </NavIcon>
                <NavText style={{fontSize: '1.25em'}}>
                  Generate New Link
                </NavText>
              </NavItem>
              <NavItem eventKey="redirect">
                <NavIcon>
                  <i className="fa fa-share" aria-hidden="true" style={{fontSize: '1.75em'}}></i>
                </NavIcon>
                <NavText style={{fontSize: '1.25em'}}>
                  Redirect
                </NavText>
              </NavItem>
              <NavItem eventKey="settings">
                <NavIcon>
                  <i className="fa fa-fw fa-cogs" style={{fontSize: '1.75em', verticalAlign: 'middle'}}/>
                </NavIcon>
                <NavText style={{fontSize: '1.25em'}}>
                  Settings
                </NavText>
              </NavItem>
            </SideNav.Nav>
          </SideNav>
          <main>
            <Route path="/" exact component={props => <Dashboard />}/>
            <Route path="/generate-link" component={props => <GenerateLink />}/>
            <Route path="/redirect" component={props => <Redirect />}/>
            <Route path="/settings" component={props => <Setting />}/>
          </main>
        </Fragment>
      )}
      />
    </Router>
  );
}

export default App;

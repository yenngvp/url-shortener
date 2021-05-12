import Title from "../../common/title";
import {Alert, Button, Form, FormGroup, Input, Label} from "reactstrap";
import fetch from "node-fetch";
import {useEffect, useState} from "react";
import {API_SERVER} from '../../config';

function Setting() {
  const [domain, setDomain] = useState();
  const [message, setMessage] = useState();

  useEffect(() => {
    fetch(API_SERVER + "/api/settings")
      .then(res => res.json())
      .then(data => {
        if (data) {
          setDomain(data.domain);
        }
      });
  }, []);

  const submit = (event) => {
    event.preventDefault();
    if (domain == null || domain === "") {
      setMessage("Domain is required.");
      return;
    }

    setMessage(null);

    fetch(API_SERVER + "/api/settings", {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({domain})
    })
      .then(res => {
        if (res.status === 200) {
          return res.json();
        } else {
          // Found an error
          res.text().then(err => {
            setMessage(err);
          });
        }
      })
      .then(setting => {
        if (setting) {
          setMessage("Setting updated successfully to new domain: " + setting.domain);
        }
      })
      .catch(err => setMessage(err));
    ;
  }

  const handleChange = (event) => {
    setDomain(event.target.value);
  }

  const renderMessage = (message) => (
    <Alert color="info">
      {message}
    </Alert>
  );

  return (
    <div className={"content"}>
      <Title title={"Settings"}/>
      {message && renderMessage(message)}
      <Form onSubmit={submit}>
        <FormGroup>
          <Label for="domain">Configure shorten URL domain (*)</Label>
          <Input type="text" name="domain" id="domain"
                 value={domain}
                 onChange={handleChange}
                 placeholder={"Type a domain..."}/>
        </FormGroup>
        <br/>
        <Button color="info">Submit</Button>
      </Form>
    </div>
  );
}

export default Setting;
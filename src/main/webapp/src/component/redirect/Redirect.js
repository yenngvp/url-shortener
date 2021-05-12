import Title from "../../common/title";
import {Alert, Button, Form, FormGroup, Input, Label} from "reactstrap";
import fetch from "node-fetch";
import {useState} from "react";
import {API_SERVER} from '../../config';

function Redirect() {
  const [shortUrl, setShortUrl] = useState();
  const [errorMessage, setErrorMessage] = useState();

  const submit = (event) => {
    event.preventDefault();
    if (shortUrl == null || shortUrl === "") {
      setErrorMessage("URL is required.");
      return;
    }

    setErrorMessage(null);

    fetch(API_SERVER + "/api/links/revert", {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({shortUrl})
    })
      .then(res => {
        if (res.status === 200) {
          return res.json();
        } else {
          // Found an error
          res.text().then(err => {
            setErrorMessage(err);
          });
        }
      })
      .then(data => {
        if (data != null) {
          window.open(data.url);
        }
      })
      .catch(err => setErrorMessage(err));
    ;
  }

  const handleChange = (event) => {
    setShortUrl(event.target.value);
  }

  const renderErrorMessage = (message) => (
    <Alert color="danger">
      {message}
    </Alert>
  );

  return (
    <div className={"content"}>
      <Title title={"Redirect"}/>
      {errorMessage && renderErrorMessage(errorMessage)}
      <Form onSubmit={submit}>
        <FormGroup>
          <Label for="shortUrl">Enter a short URL (*)</Label>
          <Input type="text" name="shortUrl" id="shortUrl" onChange={handleChange} placeholder={"Type your URL..."}/>
        </FormGroup>
        <br/>
        <Button color="info">Submit</Button>
      </Form>
    </div>
  );
}

export default Redirect;
import Title from '../../common/title';
import {Button, Form, FormGroup, Label, Input, Alert} from 'reactstrap';
import fetch from 'node-fetch';
import {useState} from "react";
import {Link} from 'react-router-dom';
import {API_SERVER} from '../../config';

function GenerateLink() {
  const [url, setUrl] = useState();
  const [showForm, setShowForm] = useState(true);
  const [link, setLink] = useState();
  const [errorMessage, setErrorMessage] = useState();

  const submit = (event) => {
    event.preventDefault();
    if (url == null || url === "") {
      setErrorMessage("URL is required.");
      return;
    }

    setErrorMessage(null);

    fetch(API_SERVER + "/api/links", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({url})
    })
      .then(res => {
        if (res.status === 200) {
          return res.json();
        } else {
          // Found an error
          res.text().then(err => {
            setErrorMessage(err);
            setShowForm(false);
          });
        }
      })
      .then(data => {
        if (data != null) {
          setLink(data);
          setShowForm(false);
        }
      })
      .catch(err => setErrorMessage(err));
    ;
  }

  const handleChange = (event) => {
    setUrl(event.target.value);
  }

  const handleAddMore = () => setShowForm(true);

  const renderCreateResult = (result) => (
    <div>
      <h3><b>Link created successfully</b></h3>
      <br/>
      <h6>ID: {result.id}</h6>
      <h6>Short URL: <a href={result.shortUrl}>{result.shortUrl}</a></h6>
      <h6>URL: <a href={result.url}>{result.url}</a></h6>
      <h6>Created Date: {result.createdAt}</h6>
    </div>
  );

  const renderErrorMessage = (message) => (
    <Alert color="danger">
      {message}
    </Alert>
  );

  return (
    <div className={"content"}>
      <Title title={"Generate a new short link"}/>
      {errorMessage && renderErrorMessage(errorMessage)
      }
      {showForm ?
        (<Form onSubmit={submit}>
            <FormGroup>
              <Label for="url">Enter a URL (*)</Label>
              <Input type="text" name="url" id="url" onChange={handleChange} placeholder={"Type your URL..."}/>
            </FormGroup>
            <br/>
            <Button color="info">Submit</Button>
          </Form>
        ) :
        (
          <>
            {!errorMessage && renderCreateResult(link)
            }
            <br/>
            <Button color="info" onClick={handleAddMore}>Add More</Button>{"  "}
            <Link to="/">View List</Link>
          </>
        )
      }
    </div>
  );
}

export default GenerateLink;
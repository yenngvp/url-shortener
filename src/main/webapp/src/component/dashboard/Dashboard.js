import {useEffect, useState} from "react";
import {Table} from 'reactstrap';
import Title from '../../common/title';
import fetch from "node-fetch";
import {API_SERVER} from '../../config';

function Dashboard() {
  const [links, setLinks] = useState([]);

  useEffect(() => {
    fetch(API_SERVER + "/api/links")
      .then(response => response.json())
      .then(data => {
        setLinks(data);
      });
  }, []);

  const renderDataRow = (link) => (
    <tr key={link.id}>
      <th scope="row">{link.id.substr(0, 8) + '...'}</th>
      <td><a href={link.shortUrl}>{link.shortUrl}</a></td>
      <td><a href={link.url}>{link.url}</a></td>
      <td>{new Date(link.createdAt).toLocaleString()}</td>
    </tr>
  );

  const renderEmptyRow = () => (
    <tr>
      <th scope="row"></th>
      <td></td>
      <td>No data.</td>
      <td></td>
    </tr>
  );

  return (
    <div className={"content"}>
      <Title title={"Dashboard"}/>
      <Table>
        <thead>
        <tr>
          <th>ID</th>
          <th>Short URL</th>
          <th>URL</th>
          <th>Created Date</th>
        </tr>
        </thead>
        <tbody>
        {links.length > 0 ?
          links.map(link => renderDataRow(link)) :
          renderEmptyRow()
        }
        </tbody>
      </Table>
    </div>
  );
}

export default Dashboard;
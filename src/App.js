import './App.css';
import AppBar from '@mui/material/AppBar';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Unstable_Grid2';
import FileUploader from './components/FileUploader';
import DataBrowser from './components/DataBrowser';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';
import FileUploadIcon from '@mui/icons-material/FileUpload';
import TableViewIcon from '@mui/icons-material/TableView';
import Paper from '@mui/material/Paper';
import Link from '@mui/material/Link';
import {
  HashRouter, Route, Switch
} from 'react-router-dom';
import {Link as RouterLink}from 'react-router-dom';

function App() {
  return (
    <HashRouter>
    <div className="App">
      <AppBar position="static">
          <Typography variant="h4" sx={{my:1}} >
            多源异构灾情数据的一体化编码管理
          </Typography>
      </AppBar>

      <Grid container spacing={4}>
            <Grid item xs={2}>
              <List>
                <ListItem sx={{mt:3}}>
                  <ListItemButton>
                    <ListItemIcon>
                      <FileUploadIcon/>
                    </ListItemIcon>
                  <Link component={RouterLink} to="/" underline="none">
                      <ListItemText>
                        上传文件                        
                      </ListItemText>
                    </Link>
                  </ListItemButton>
                </ListItem>
                <Divider/>
                <ListItem>
                  <ListItemButton>
                    <ListItemIcon>
                      <TableViewIcon/>
                    </ListItemIcon>
                    <Link component={RouterLink} to="/data" underline="none">
                      <ListItemText>
                          查询数据
                      </ListItemText>
                    </Link>
                  </ListItemButton>
                  
                </ListItem>
                <Divider/>
              </List>
            </Grid>
            <Grid item xs={10}> 
            <Switch>
              <Paper className="paper" sx={{m:6, p:6}} elevation={3}>
                <Route path="/" exact component={FileUploader}/>
                <Route path="/data" component={DataBrowser}/>
              </Paper>
            </Switch>
            </Grid>
      </Grid>
    </div>
    </HashRouter>
  );
}

export default App;
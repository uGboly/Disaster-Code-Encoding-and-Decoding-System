import './App.css';
import AppBar from '@mui/material/AppBar';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Unstable_Grid2';
import FileUploader from './components/FileUploader';

function App() {
  return (
    <div className="App">
      <AppBar position="static">
          <Typography variant="h4" sx={{my:1}} >
            多源异构灾情数据的一体化编码管理
          </Typography>
      </AppBar>

      <Grid container spacing={2}>
            <Grid item xs={2}>
            </Grid>
            <Grid item xs={8}> 
              <FileUploader/>
            </Grid>
            <Grid item xs={2}>
            </Grid>
      </Grid>
    </div>
  );
}

export default App;

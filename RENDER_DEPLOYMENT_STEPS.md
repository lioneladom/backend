# Deploy FitTrack Backend to Render

## Step-by-Step Deployment Guide

### Prerequisites
✅ Your backend is configured for Supabase PostgreSQL
✅ `render.yaml` configuration file is ready
✅ Database password: `FitTrack@1234`

### Step 1: Prepare Your Repository
1. Make sure your code is pushed to GitHub
2. Ensure the `backend1` folder contains all the configuration files

### Step 2: Create Render Account
1. Go to [render.com](https://render.com)
2. Sign up with your GitHub account
3. Authorize Render to access your repositories

### Step 3: Create New Web Service
1. Click "New +" → "Web Service"
2. Connect your GitHub repository
3. Select your repository from the list

### Step 4: Configure the Service
**Basic Settings:**
- **Name:** `fittrack-backend`
- **Region:** Choose closest to your users
- **Branch:** `main` (or your default branch)
- **Root Directory:** `backend1`

**Build & Deploy:**
- **Runtime:** `Java`
- **Build Command:** `mvn clean package -DskipTests`
- **Start Command:** `java -jar target/fittrack-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`

### Step 5: Set Environment Variables
Add these environment variables in Render dashboard:

**Required Variables:**
```
SPRING_PROFILES_ACTIVE=prod
JWT_SECRET=VGhpcyBpcyBhIHZlcnkgc2VjdXJlIGFuZCBsb25nIHJhbmRvbSBzZWNyZXQga2V5IQpKQk1uU2h2d3p5Q2Z4d2VydGh5dWlvcGFzZGZnUHJvZHVjdGlvbktleVJlbmRlcg==
SUPABASE_URL=https://qindoeldnsrybeixezhs.supabase.co
SUPABASE_SERVICE_ROLE_KEY=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFpbmRvZWxkbnNyeWJlaXhlemhzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc1MzgzNTkzNCwiZXhwIjoyMDY5NDExOTM0fQ.fp69aqLIE8UgYIwjh9MmFC9EcFBY917UXvH1k1GlAko
ALLOWED_ORIGINS=https://your-frontend-domain.com
```

### Step 6: Deploy
1. Click "Create Web Service"
2. Render will start building your application
3. Wait for the build to complete (5-10 minutes)
4. Your app will be available at: `https://fittrack-backend.onrender.com`

### Step 7: Verify Deployment
Test these endpoints:
1. **Health Check:** `GET https://your-app.onrender.com/actuator/health`
2. **API Test:** `GET https://your-app.onrender.com/api/test` (if you have one)

### Step 8: Update Frontend Configuration
Update your frontend to use the new backend URL:
```javascript
const API_BASE_URL = 'https://fittrack-backend.onrender.com/api';
```

## Important Notes

### Free Tier Limitations
- Apps sleep after 15 minutes of inactivity
- First request after sleep takes 30-60 seconds to wake up
- 750 hours/month free (enough for testing)

### Database Connection
- Your app will automatically connect to Supabase PostgreSQL
- Database schema will be created on first run
- Check Supabase dashboard for connection logs

### Troubleshooting
1. **Build Fails:** Check build logs in Render dashboard
2. **App Won't Start:** Verify environment variables are set
3. **Database Issues:** Check Supabase connection in logs
4. **CORS Errors:** Update `ALLOWED_ORIGINS` with your frontend domain

### Monitoring
- View logs in Render dashboard
- Monitor health at `/actuator/health`
- Check database connections in Supabase dashboard

## Next Steps After Deployment
1. Test all API endpoints
2. Update frontend to use new backend URL
3. Configure CORS for your frontend domain
4. Set up monitoring and alerts
5. Consider upgrading to paid plan for production use

## Support
- Render Documentation: [render.com/docs](https://render.com/docs)
- Supabase Documentation: [supabase.com/docs](https://supabase.com/docs)
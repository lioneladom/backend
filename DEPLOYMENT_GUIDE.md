# FitTrack Backend Deployment Guide

## Overview
This guide will help you deploy your FitTrack backend to run online with Supabase PostgreSQL database.

## Prerequisites
- Supabase project with PostgreSQL database
- Database password: `FitTrack@1234`
- Supabase URL: `https://qindoeldnsrybeixezhs.supabase.co`

## Configuration Files Created
- `application-prod.properties` - Production configuration with Supabase PostgreSQL
- `railway.toml` - Railway deployment configuration
- `render.yaml` - Render deployment configuration

## Deployment Options

### Option 1: Railway (Recommended)
1. Create account at [railway.app](https://railway.app)
2. Connect your GitHub repository
3. Deploy from the `backend1` folder
4. Railway will automatically detect the `railway.toml` configuration
5. Set environment variables:
   - `SPRING_PROFILES_ACTIVE=prod`
   - `JWT_SECRET=<generate-secure-secret>`

### Option 2: Render
1. Create account at [render.com](https://render.com)
2. Create new Web Service
3. Connect your GitHub repository
4. Set build command: `mvn clean package -DskipTests`
5. Set start command: `java -jar target/fittrack-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`
6. Set environment variables as listed in `render.yaml`

### Option 3: Heroku
1. Create account at [heroku.com](https://heroku.com)
2. Install Heroku CLI
3. Run these commands:
```bash
cd backend1
heroku create your-app-name
heroku config:set SPRING_PROFILES_ACTIVE=prod
heroku config:set JWT_SECRET=<your-secure-secret>
git add .
git commit -m "Deploy to Heroku"
git push heroku main
```

## Environment Variables Needed
- `SPRING_PROFILES_ACTIVE=prod`
- `JWT_SECRET=<secure-random-string>`
- `SUPABASE_URL=https://qindoeldnsrybeixezhs.supabase.co`
- `SUPABASE_SERVICE_ROLE_KEY=<your-service-role-key>`
- `ALLOWED_ORIGINS=<your-frontend-domain>`

## Database Schema
The application will automatically create the database schema on first run using Hibernate's `ddl-auto=update` setting.

## Health Check
Your deployed application will have a health check endpoint at:
`https://your-domain.com/actuator/health`

## Testing Deployment
1. Check health endpoint: `GET /actuator/health`
2. Test authentication: `POST /api/auth/signin`
3. Test user endpoints: `GET /api/users/profile`

## Troubleshooting
- Check application logs in your deployment platform
- Verify database connection in Supabase dashboard
- Ensure all environment variables are set correctly
- Check that the PostgreSQL driver is included in dependencies

## Next Steps
1. Choose your deployment platform
2. Set up the deployment
3. Configure environment variables
4. Test the deployed endpoints
5. Update your frontend to use the new backend URL
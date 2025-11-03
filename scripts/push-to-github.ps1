<#
Simple helper to add a remote and push the current repository to GitHub.
Usage:
  - Run this script and provide the remote repo URL when prompted (for example: https://github.com/youruser/yourrepo.git)
  - Alternatively create an empty repo on GitHub via the web UI and copy the HTTPS remote URL.

This script will set origin, ensure the main branch, and push the initial commit.
#>

param()

Write-Host "This helper will add a remote 'origin' and push the current branch to GitHub." -ForegroundColor Cyan
$remote = Read-Host "Enter the Git remote URL (HTTPS), or leave blank to cancel"
if ([string]::IsNullOrWhiteSpace($remote)) {
    Write-Host "Cancelled." -ForegroundColor Yellow
    exit 1
}

Write-Host "Adding remote origin -> $remote"
git remote remove origin 2>$null || $null
git remote add origin $remote

Write-Host "Ensuring branch 'main' exists and is checked out"
git branch -M main

Write-Host "Pushing to origin/main (this may prompt for credentials)"
git push -u origin main

if ($LASTEXITCODE -eq 0) {
    Write-Host "Push successful." -ForegroundColor Green
} else {
    Write-Host "Push failed. Check your credentials or remote URL." -ForegroundColor Red
}

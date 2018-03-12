if [[ $(git diff HEAD@{1}..HEAD@{0} -- "${file}" | wc -l) -gt 0 ]]; then
    echo
    echo -e "======> The file "${file}" changed!"
    echo
    git diff --color HEAD@{1}..HEAD@{0} -- "${file}" | sed 's/^/        /' | tail -n+5
    echo
else
    echo " Increment file version in VERSION FILE "
    exit 1
fi

image_version=`cat VERSION`
cp target/scala-2.11/spark-work-assembly-1.0.jar 'target/scala-2.11/spark-work-assembly-'$image_version'.jar'
sudo aws s3 cp 'target/scala-2.11/spark-work-assembly-'$image_version'.jar' 's3://data-platform-jobs/master_job/spark-work-assembly-'$image_version'.jar'

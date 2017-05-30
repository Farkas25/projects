using UnityEngine;
using System.Collections;

public class Raining : MonoBehaviour {
    private Vector3 startPos;
    public GameObject cloud;
    private float speed;
    private float targetY;
    private Vector3 cloudDistance;
    private Vector3 cloudStartPos;
	// Use this for initialization
	void Start () {
        startPos = transform.position;
        speed = Random.Range(5.0f, 8.0f);
        targetY = Random.Range(3.0f, 4.0f);
        cloudDistance = cloud.transform.position - transform.position;
        cloudStartPos = cloud.transform.position;
	}
	
	// Update is called once per frame
	void Update () {
        float dt = Time.deltaTime;

        if (cloud.transform.position != cloudStartPos)
        {
            transform.position = new Vector3(cloud.transform.position.x + cloudDistance.x, transform.position.y + cloudDistance.y, transform.position.z);
            cloudStartPos = cloud.transform.position;
            cloudDistance = cloud.transform.position - transform.position;
            startPos = transform.position;
        }

        if (transform.position.y > cloud.transform.position.y - targetY)
        {        
            transform.position = transform.position + Vector3.down * speed * dt;
        }
        else
        {
            transform.position = startPos;
            speed = Random.Range(5.0f, 8.0f);
            targetY = Random.Range(3.0f, 4.0f);
        }
    }
}
